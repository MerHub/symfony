if(executeSpeech==true){
    recognition.addEventListener('result', e => {

        var transcript = Array.from(e.results).map(result => result[0]).map(result => result.transcript).join('');
        var poopScript = transcript.replace(/poop|poo|shit|dump/gi, ' ');

        if (e.results[0].isFinal) {
            if(poopScript!=""){
                var p = poopScript
                for(j=0;j<p.length;j++){
                    p=p.toString().replace(" ","")
                }
                analyseurVocal(p)
            }

            // p = document.createElement('p');

            // words.appendChild(p);

        }

    });
    recognition.onspeechend = function() {
        $(".micro").click();
        recognition.stop();
    }
    // recognition.addEventListener('end', recognition.start);
}

function analyseurVocal(instruction){
    // navigation map
    if(instruction.indexOf("disSiri")!=-1) {
        var operations = [["déplacer", "déplace", "déplacez", "déplacé", "bouge", "bouger", "bougez", "bougé", "mouvoir", "déporter", "déporté", "décaler", "décalez", "décalé", "circuler", "circulé", "circulez"]]
        var directions = [["nord-est","nordest"], ["nord-ouest","nordouest"], ["sud-ouest","sudouest"], ["sud-est","sudest"],["gauche", "Est"], ["droite", "Ouest"], ["haute", "Nord"], ["bas", "Sud"]]

        var reponseOperation = -1;
        var reponseDirections = -1;

        var j = 0;
        for (j = 0; j < operations.length; j++) {
            reponseOperation = analyseurVocalImbriquer(instruction, operations[j]);
            if(reponseOperation != -1){
                reponseOperation=j;
                break;
            }
        }
        if (reponseOperation != -1) {
            for (j = 0; j < directions.length; j++) {
                reponseDirections = analyseurVocalImbriquer(instruction, directions[j]);
                if(reponseDirections != -1){
                    reponseDirections=j;
                    break;
                }
            }

            (reponseDirections != -1) ? (
                (reponseOperation==0 && reponseDirections==4) ? (vitesseLat=0,vitesseLng=-0.0001) : "",
                    (reponseOperation==0 && reponseDirections==5) ? (vitesseLat=0,vitesseLng=0.0001) : "",
                    (reponseOperation==0 && reponseDirections==6) ? (vitesseLng=0,vitesseLat=0.0001) : "",
                    (reponseOperation==0 && reponseDirections==7) ? (vitesseLng=0,vitesseLat=-0.0001) : "",
                    (reponseOperation==0 && reponseDirections==0) ? (vitesseLat=0.0001,vitesseLng=-0.0001) : "",
                    (reponseOperation==0 && reponseDirections==1) ? (vitesseLat=0.0001,vitesseLng=0.0001) : "",
                    (reponseOperation==0 && reponseDirections==2) ? (vitesseLat=-0.0001,vitesseLng=0.0001) : "",
                    (reponseOperation==0 && reponseDirections==3) ? (vitesseLat=-0.0001,vitesseLng=-0.0001) : ""
            ) : (speak("je n'ai pas compris"));

        } else {
            speak("je n'ai pas compris");
        }

    }else{
        speak("{{ user.username }}, Vous devez dire 'dis Siri' avant toute opération ");
    }


}

function analyseurVocalImbriquer(instruction,tableau){
    j=0;
    for(j=0;j<tableau.length;j++){
        if(instruction.replace(" ","").toLowerCase().indexOf(tableau[j].toLowerCase())!=-1){
            return j;
        }
    }
    return -1;
}