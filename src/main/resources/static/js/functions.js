
function supprimer( resource , id ) {
    if (confirm("Êtes vous sûr ?")) {
        window.location = '/'+resource+'/delete/' + id;
    }
}

function load(){
    let paysSelected = document.getElementById('pays').value;

    fetch( "villeajax?pays="+paysSelected ) // si on ne spécifie rien, l'appel fait est un GET
        .then(res => {
            return res.text(); // extraction du texte à partir de l'objet response de fetch API
        })
        .then(data => {
            document.getElementById('ville').innerHTML = data;
        })
        .catch(error => alert("Erreur : " + error));
}

setTimeout(function(){
    let error = document.getElementById("errMessage");
    let success = document.getElementById("succMessage");

    if (error != null)
        error.style.display = "none";
    if (success != null)
        success.style.display = "none";
}, 5000);