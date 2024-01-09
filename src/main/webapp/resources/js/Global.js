 
          const RGPDWINDOW= document.getElementById('rgpd');
              const btnRgpdAccept= document.getElementById('rgpdAccept');
              const btnRgpdUnaccept= document.getElementById('rgpdUnaccept');
              const rgpdAccept = "rgpdAccept";


              RGPDWINDOW.style.display= 'none';

              

        document.addEventListener('DOMContentLoaded', function() {
			


        	const menus = document.getElementById('menus');
            menus.addEventListener('click',()=>{ menus.classList.toggle("desable"); })


           const ctx1 = document.getElementById('ChartBar').getContext('2d');
           const ctx2 = document.getElementById('Chartline').getContext('2d');

           const Chartbar = chard(ctx1,'bar')
           const Chartline = chard(ctx2,'line')
     


            const existeCle1 = verifierCleLocalStorage(rgpdAccept);


            let isAccept ;

            btnRgpdAccept.addEventListener("click", function (){
                isAccept=true;
                return saveLocalStorage()
            } );

            btnRgpdUnaccept.addEventListener('click', function (){
                isAccept=false;
                return  saveLocalStorage()
            } );

            console.log(`La clé "${rgpdAccept}" existe : ${existeCle1}`);

            function saveLocalStorage() {
                // Vérifier si le localStorage est disponible dans le navigateur
                if (typeof localStorage !== 'undefined') {
                    // Stocker la valeur dans le localStorage avec la clé spécifiée
                    localStorage.setItem(rgpdAccept, isAccept);
                } else {
                    console.error('Le localStorage n\'est pas pris en charge dans ce navigateur.');
                }
            }


            function chard (_ctx, type){
                let resChart = new Chart(_ctx, {
                     type: type,
                     data: {
                         labels: ['January', 'February', 'March', 'April', 'May'],
                         datasets: [{
                             label: 'Monthly Sales',
                             data: [50, 75, 120, 200, 90],
                             backgroundColor: 'rgba(75, 192, 192, 0.2)',
                             borderColor: 'rgb(87,192,75)',
                             borderWidth: 1
                         },
                             {
                                 label: 'Monthly Sales',
                                 data: [70, 5, 250, 20, 75],
                                 backgroundColor: 'red',
                                 borderColor: 'blue',
                                 borderWidth: 1
                             }]
                     },
                     options: {
                         scales: {
                             y: {
                                 beginAtZero: true
                             }
                         }
                     }
                 });
                 return resChart
             }
                 

            function verifierCleLocalStorage(cle) {

                if (typeof localStorage !== 'undefined') {
                    // Vérifier si la clé existe dans le localStorage

                    return  localStorage.getItem(cle) != null ?  RGPDWINDOW.style.display= 'none' :  RGPDWINDOW.style.display= 'flex'; ;

                } else {
                    console.error('Le localStorage n\'est pas pris en charge dans ce navigateur.');
                    return false;
                }
            }

           
        });
        
        

      

       