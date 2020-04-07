function speak(msg){
    var utterThis = new SpeechSynthesisUtterance(msg);

    for(i = 0; i < voices.length ; i++) {
        if(voices[i].name === "Microsoft Hortense - French (France)") {
            utterThis.voice = voices[i];
        }
    }
    synth.speak(utterThis);
}