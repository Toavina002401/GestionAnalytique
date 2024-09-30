
// Obtenir les éléments
const modal = document.getElementById("myModal");
const btn = document.getElementById("openModalBtn");
const span = document.getElementsByClassName("close")[0];

// Ouvrir le modal lorsque le bouton est cliqué
btn.onclick = function() {
    modal.style.display = "block";
}

// Fermer le modal lorsque l'utilisateur clique sur <span> (x)
span.onclick = function() {
    modal.style.display = "none";
}

// Fermer le modal lorsque l'utilisateur clique en dehors du contenu du modal
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}






// Obtenir les éléments
const modalCentre = document.getElementById("myModalCentre");
const btnCentre = document.getElementById("openModalBtnCentre");
const spanCentre = document.getElementsByClassName("closeCentre")[0];

// Ouvrir le modal lorsque le bouton est cliqué
btnCentre.onclick = function() {
    modalCentre.style.display = "block";
}

// Fermer le modalCentre lorsque l'utilisateur clique sur <span> (x)
spanCentre.onclick = function() {
    modalCentre.style.display = "none";
}

// Fermer le modalCentre lorsque l'utilisateur clique en dehors du contenu du modalCentre
window.onclick = function(event) {
    if (event.target == modalCentre) {
        modalCentre.style.display = "none";
    }
}





