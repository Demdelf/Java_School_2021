var q = ${cart.quantity}
    function addProd(id) {
        var xhttp = new XMLHttpRequest();
        xhttp.open("POST", "/cart/add/" + id, true);
        xhttp.send();
        q = q + 1
        document.getElementById("cartQ").innerHTML = q;
    }
