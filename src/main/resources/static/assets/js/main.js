document.addEventListener("DOMContentLoaded", function(){
            var form = document.getElementById("search-form");

            form.addEventListener('submit', function(e){
                e.preventDefault();
                let distancia = form.querySelector('#distancia').value;
                let prioridade = form.querySelector('#prioridade').value;
                let tipo_transporte = form.querySelector('#tipo-transport').value;
                tipo_transporte = (tipo_transporte) ? tipo_transporte : '';
                
                let url = 'http://localhost:8080/transportadoras/'+ prioridade +'/' + distancia + '/' + tipo_transporte;
                
                console.log(url);
                let request = new XMLHttpRequest();

                
                request.open('get',  url, true);
                request.onload = function() {
                    
                    
                    if(request.status == 200){
                    	console.log(request.response);
                    	let result = JSON.parse(request.response);
                    	let container = document.getElementById("trans-container");
                    	container.innerHTML = '';
                    	for (let transportadora of result){
                    		
							let transp = document.createElement('div');
							transp.setAttribute('class', 'transportadora');
							
							let nome = document.createElement('h2');
							nome.innerHTML = transportadora.nome;
							transp.appendChild(nome);

                    		let fretes = document.createElement('div');
                    		fretes.setAttribute('class', 'frete-container');
                    		
                        	for(let frete of transportadora.fretes){
                        		let f = document.createElement('div');
                        		let tipo = (frete.tipo == 1) ? 'Avião -' : 'Terrestre';
                        		f.innerHTML = tipo + " preço: " + frete.valorKm * distancia / 10 + ' | tempo estimado:' + Math.floor( distancia * frete.tempoKm / 60)  + 'min';
                        		fretes.appendChild(f);
                        		
                        	}
                        	transp.appendChild(fretes);
                    		container.appendChild(transp);
                    	}
                        
                    }
                }

                request.send();
                
            });
        });