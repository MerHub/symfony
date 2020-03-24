
//le type de navigateur
var Version = navigator.appVersion;
// initiation des speech
var executeSpeech= (navigator.appVersion.toString().toLowerCase().indexOf("edge")!=-1) ? false : true;
//initiation des variable locales
var recognition="";
var synth="";
var voices="";
if(executeSpeech==true){
    // activer le speech recognition
    window.SpeechRecognition = window.SpeechRecognition || window.webkitSpeechRecognition;

    //initiation des variable locales
    synth = window.speechSynthesis;
    recognition = new SpeechRecognition();
    recognition.interimResults = true;
    recognition.lang = 'fr-FR';
    voices = synth.getVoices();

    for(i = 0; i < voices.length ; i++) {
        var option = document.createElement('option');
        option.textContent = voices[i].name + ' (' + voices[i].lang + ')';
        option.setAttribute('data-lang', voices[i].lang);
        option.setAttribute('data-name', voices[i].name);
    }
}
var ty=0;
var nbr_click=0;
L.mapbox.accessToken = 'pk.eyJ1IjoiY3lyaWxsZTgwMCIsImEiOiJjazZzcmU2bHUwMDh0M2xxcGRtOGJndGh4In0.zqSmLTyuMbkLZQyVFgtAXA';
var loader = document.getElementById('loader');
// centrer la map
var map = L.mapbox.map('map').setView([moiX,moiY], 17);
// initialisation de la legende
map.legendControl.addLegend("vide");
map.attributionControl.setPosition('bottomleft');
// fullscreen la map
L.control.fullscreen().addTo(map);
// initialisation des chemin
var directions = L.mapbox.directions();
var directionsLayer = L.mapbox.directions.layer(directions).addTo(map);
var directionsRoutesControl = L.mapbox.directions.routesControl('routes', directions).addTo(map);
var directionChauffeur = L.mapbox.directions();
// start the loading screen
function startLoading() {
    loader.className = '';
}
startLoading();
