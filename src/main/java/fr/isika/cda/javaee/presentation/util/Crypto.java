package fr.isika.cda.javaee.presentation.util;

import java.text.MessageFormat;
import java.util.Random;

import javax.inject.Inject;

import fr.isika.cda.javaee.dao.user.IDaoUser;

public class Crypto {
	@Inject
	private static IDaoUser userDao;

	public static String BLUR_FOR_LENGTH = "3657";
	public static int UNIT_MASK_VALUE = 100;
	public static int CHARACTER_FORMAT_SIZE = 2;
	public static int DECODING_KEY_FORMAT_SIZE = 4;
	public static int BLURING_CHARACTER_SIZE = 1;
	public static int DATAS_STARTING_INDEX = 1;
	public static int ENCRYPTED_DATAS_STARTING_INDEX = 0;
	public static int CHARACTER_INDEX = DECODING_KEY_FORMAT_SIZE + BLURING_CHARACTER_SIZE;
	public static int PACK_LENGTH = DECODING_KEY_FORMAT_SIZE + CHARACTER_FORMAT_SIZE + BLURING_CHARACTER_SIZE;
	public static int[] MODULO_MASK_VALUE = new int[] { 99, 101, 86, 120, 91 };
	public static int[] FACTOR_MASK_VALUE = new int[] { 17, 22, 16, 25, 12, 9 };
	public static int[] GAP_MASK_VALUE = new int[] { 124, 98, 175, 88, 72 };

	/**
	 * Transform a string in a chain of numbers with partials keys inside to decode
	 * it.
	 * 
	 * @param standardizedDatasToEncrypt (:String) The string to encrypt.
	 * @return encryptedDatasToReturn (:String) chain of numbers.
	 */
	public static String EncryptDataInNumbers(String datasToEncrypt, int standardSizeOfDatas) {
		String ecryptedDatasToReturn = "";
		// Define the length of the message an integrate it at the beginning of the
		// chain.
		int messageLength = datasToEncrypt.length() > standardSizeOfDatas ? standardSizeOfDatas
				: datasToEncrypt.length();
		String messageLengthEncrypted = BLUR_FOR_LENGTH + String.valueOf(UNIT_MASK_VALUE + messageLength);
		ecryptedDatasToReturn += messageLengthEncrypted;
		String standardizedDatasToEncrypt = ResizeDatas(datasToEncrypt, standardSizeOfDatas);
		for (int i = ENCRYPTED_DATAS_STARTING_INDEX; i < standardizedDatasToEncrypt.length(); i++) {
			String encryptedCharacter = EncryptCharacter(i, standardizedDatasToEncrypt.charAt(i));
			ecryptedDatasToReturn += encryptedCharacter;
		}
		// Add the encrypted data group to the main encrypted chain.
		return ecryptedDatasToReturn;
	}

	/**
	 * Decrypt and encrypted message and return it as a readable string.
	 * 
	 * @param encryptedDatas (:String) The string to decrypt.
	 * @return decryptDatasToReturn (:String) Readable decrypted string.
	 */
	public static String DecryptDataInWords(String encryptedDatas) {
		String decryptDatasToReturn = "";
		// Length of the original message is stocked between the index 4 and 6 of the
		// encryptedDatas with a blurring value of 100.
		int datasLength = Integer.parseInt(encryptedDatas.substring(BLUR_FOR_LENGTH.length(), PACK_LENGTH))
				- UNIT_MASK_VALUE;
		// encryptedDatas is structure with packs of seven characters. First pack is
		// useless.
		for (int i = DATAS_STARTING_INDEX; i <= datasLength; i++) {
			// extract the key from the encrypted sequence.
			int characterDecodingKey = Integer.parseInt(encryptedDatas.substring(PACK_LENGTH * i,
					PACK_LENGTH * i + (CHARACTER_INDEX - BLURING_CHARACTER_SIZE)));
			// extract the encrypted data from the encrypted chain.
			int cleanedEncodeCharacter = Integer.parseInt(
					encryptedDatas.substring(PACK_LENGTH * i + CHARACTER_INDEX, PACK_LENGTH * i + PACK_LENGTH));
			// Decrypt data using the key.
			char decodeCharacter = (char) ((MODULO_MASK_VALUE[(i - DATAS_STARTING_INDEX) % MODULO_MASK_VALUE.length]
					* characterDecodingKey + cleanedEncodeCharacter)
					/ FACTOR_MASK_VALUE[(i - DATAS_STARTING_INDEX) % FACTOR_MASK_VALUE.length]
					- GAP_MASK_VALUE[(i - DATAS_STARTING_INDEX) % GAP_MASK_VALUE.length]);
			decryptDatasToReturn += decodeCharacter;
		}
		return decryptDatasToReturn;
	}

	/**
	 * Resized a string data to it's standard size, to be properly save in the
	 * database.
	 * 
	 * @param datasToResize    (:String) Data which size hasn't been standardized.
	 * @param finalSizeOfDatas (:int) Standard size of the given kind of data.
	 * @return resizedDatas (:String) Data which size has been standardized.
	 */
	private static String ResizeDatas(String datasToResize, int finalSizeOfDatas) {
		String resizedDatas;
		// Size upper the standard value. Truncate the data.
		if (datasToResize.length() > finalSizeOfDatas) {
			resizedDatas = datasToResize.substring(0, finalSizeOfDatas);
			// Size at the standard value.
		} else if (datasToResize.length() == finalSizeOfDatas) {
			resizedDatas = datasToResize;
			// Size under the standard value.
		} else {
			resizedDatas = datasToResize;
			for (int i = (datasToResize.length() - 1); i < finalSizeOfDatas; i++) {
				// Generate a random character.
				Random randomChar = new Random();
				resizedDatas += (char) (randomChar.nextInt(26) + 97);
			}
		}
		return resizedDatas;
	}

	/**
	 * Encrypt one character of the message and return it.
	 * 
	 * @param i                  (:int) index of the character in the message.
	 * @param characterToEncrypt (:char) The character to encrypt.
	 * @return encryptedCharacter (:String) The encrypted character to add in the
	 *         chain.
	 */
	private static String EncryptCharacter(int i, char characterToEncrypt) {
		// Prepare the elements for the encryption.
		int encodedCharacterInNumber = (characterToEncrypt + GAP_MASK_VALUE[i % GAP_MASK_VALUE.length])
				* FACTOR_MASK_VALUE[i % FACTOR_MASK_VALUE.length] % MODULO_MASK_VALUE[i % MODULO_MASK_VALUE.length];
		int characterDecodingKey = (characterToEncrypt + GAP_MASK_VALUE[i % GAP_MASK_VALUE.length])
				* FACTOR_MASK_VALUE[i % FACTOR_MASK_VALUE.length] / MODULO_MASK_VALUE[i % MODULO_MASK_VALUE.length];
		int bluringNumber = (int) (Math.random() * 10);
		// Concat all elements to create the encrypted data group.
		String encryptedCharacter = String.format(MessageFormat.format("%0{0}d", DECODING_KEY_FORMAT_SIZE),
				characterDecodingKey)
				+ String.valueOf(bluringNumber).concat(
						String.format(MessageFormat.format("%0{0}d", CHARACTER_FORMAT_SIZE), encodedCharacterInNumber));
		return encryptedCharacter;
	}

}
