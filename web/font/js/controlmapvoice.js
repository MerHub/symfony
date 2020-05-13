function deplacerMapNord(){
    var lat=map.getCenter().lat+vitesseLat;
    map.setView(new L.LatLng(lat,map.getCenter().lng),map.getZoom());
}
function deplacerMapSud(){
    var lat=map.getCenter().lat-(vitesseLat*(-1));
    map.setView(new L.LatLng(lat,map.getCenter().lng),map.getZoom());
}
function deplacerMapEst(){
    var long=map.getCenter().lng-(vitesseLng*(-1));
    map.setView(new L.LatLng(map.getCenter().lat,long),map.getZoom());
}
function deplacerMapOuest(){
    var long=map.getCenter().lng+vitesseLng;
    map.setView(new L.LatLng(map.getCenter().lat,long),map.getZoom());
}

function deplacerMapNordOuest(){
    var lat=map.getCenter().lat+vitesseLat;
    var long=map.getCenter().lng+vitesseLng;
    map.setView(new L.LatLng(lat,long),map.getZoom());
}
function deplacerMapSudOuest(){
    var lat=map.getCenter().lat-(vitesseLat*(-1));
    var long=map.getCenter().lng+vitesseLng;
    map.setView(new L.LatLng(lat,long),map.getZoom());
}

function deplacerMapSudEst(){
    var lat=map.getCenter().lat-(vitesseLat*(-1));
    var long=map.getCenter().lng-(vitesseLng*(-1));
    map.setView(new L.LatLng(lat,long),map.getZoom());
}
function deplacerMapNordEst(){
    var lat=map.getCenter().lat+vitesseLat;
    var long=map.getCenter().lng-(vitesseLng*(-1));
    map.setView(new L.LatLng(lat,long),map.getZoom());
}