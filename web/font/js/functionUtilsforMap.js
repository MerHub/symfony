function iconStartExist() {
    for (j = 0; j < theMarker.length; j++) {
        if (theMarker[j]._popup._content == "start") {
            return j;
        }
    }
    return -1;
}

function iconEndExist() {
    for (j = 0; j < theMarker.length; j++) {
        if (theMarker[j]._popup._content == "end") {
            return j;
        }
    }
    return -1;
}

function calculDistance(lat1,lon1,lat2,lon2){
    getAdresseDebut(lat1,lon1);
    getAdresseDestination(lat2,lon2);
    updateDirection();

    var southWest = L.latLng(lat1, lon1),
        northEast = L.latLng(lat2, lon2),
        bounds = L.latLngBounds(southWest, northEast);
    map.fitBounds(bounds);

}
var listeD = [];


function supprimerIconSupplementaire(){
    $(".leaflet-marker-icon.leaflet-zoom-animated.leaflet-interactive.leaflet-marker-draggable").css("display", "none");
}
function creerIcon(lat,lon,fonction){
    var  icon;
    if(fonction=="fin"){
        icon= new L.marker([lat, lon], {
            icon: L.mapbox.marker.icon({
                'marker-size': 'large',
                'marker-symbol': 'bus',
                'marker-color': '#E91E63'
            })
        }).bindPopup('end');
    }else{
        icon= new L.marker([lat, lon], {
            icon: L.mapbox.marker.icon({
                'marker-size': 'large',
                'marker-symbol': 'warehouse',
                'marker-color': '#42AA6B'
            })
        }).bindPopup('start');
    }

    return icon;
}

function supprimerIcon(j){
    map.removeLayer(theMarker[j]);
    var index = theMarker.indexOf(theMarker[j]);
    var indexs = coordonner.indexOf(coordonner[j]);
    theMarker.splice(index, 1);
    coordonner.splice(indexs, 1)
    cmpt--;
}

function finishedLoading() {
    // first, toggle the class 'done', which makes the loading screen
    // fade out
    loader.className = 'done';
    setTimeout(function() {
        // then, after a half-second, add the class 'hide', which hides
        // it completely and ensures that the user can interact with the
        // map again.
        loader.className = 'hide';
    }, 500);
}