document.addEventListener("DOMContentLoaded", function(){
			
	
    var form = document.getElementById("search-form");

    form.addEventListener('submit', function(e){
        e.preventDefault();
        
        let rota = {
        		origem: document.getElementById("origem").value,
        		destino: document.getElementById("destino").value,
        		distancia: document.getElementById("distancia").value,
        		tipo: document.getElementById("tipo-transport").value
        }
        
        let prioridade = document.getElementById("prioridade").value;
                
        let url = 'http://localhost:8080/transportadoras/'+ prioridade;
        
        console.log(url);
        let request = new XMLHttpRequest();

        
        request.open('POST',  url, true);
        request.setRequestHeader("Content-Type","application/json");
        request.onload = function() {                    
            if(request.status == 200){
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
                		console.log(frete);
                		let f = document.createElement('div');
                		let tipo = (frete.tipo == 1) ? 'Avião -' : 'Terrestre';
                		f.innerHTML = tipo + " preço: " + frete.valorKm * rota.distancia / 10 + ' | tempo estimado:' + Math.floor( rota.distancia * frete.tempoKm / 60)  + 'min';
                		fretes.appendChild(f);
                		
                	}
                	transp.appendChild(fretes);
            		container.appendChild(transp);
            	}
                
            }
        }

        request.send(JSON.stringify(rota));
        
    });
});