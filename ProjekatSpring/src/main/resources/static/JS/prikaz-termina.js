$(document).ready(function () {    // Čeka se trenutak kada je DOM(Document Object Model) učitan da bi JS mogao sa njim da manipuliše.
    // ajax poziv
    var uloga = localStorage.getItem("role");
    if(uloga === "admin") {
        window.location.href = "admin.html";
    } else if(uloga === "trener") {
        window.location.href = "trener.html";
    } else if(uloga === "clan") {
        uloga = "clan";
    } else if(uloga === "odjavljen" || uloga == null) {
        localStorage.setItem("role", "odjavljen");
        window.location.href = "../index.html";
    } else if(uloga !== "clan") {
        alert("Nemate pristup ovoj stranici!");
        window.location.href = "../index.html";
    }

    var id = localStorage.getItem("id-termina");
    var zastita = uloga;

    var provera = {
        id, 
        zastita
    }
    console.log("ID koji je poslan je " + id);
    $.ajax({
        type: "POST",                                              
        url: "http://localhost:8080/api/clan/prikazTerminaDetaljno",                 
        dataType: "json",  
        contentType: "application/json",                            // tip podataka koje šaljemo
        data: JSON.stringify(provera),                                              
        success: function (res) {   
            let c1 = "<td class=\"newCell center\">" + res.id + "</td>";       
            let c2 = "<td class=\"newCell center\">" + res.oznakaSale + "</td>";        
            let c3 = "<td class=\"newCell center\">" + res.korisnickoIme + "</td>";   
            let c4 = "<td class=\"newCell center\">" + (res.ocena).toFixed(2) + "</td>";       
            let c5 = "<td class=\"newCell center\">" + res.clanoviTermina + "</td>";   
            let c6 = "<td class=\"newCell center\">" + res.ocene + "</td>";   
            $('#id-termina').append(c1);      
            $('#sala').append(c2);  
            $('#trener').append(c3);  
            $('#ocena').append(c4);  
            $('#clanovi').append(c5);  
            $('#ocene').append(c6);  
        },
        error: function (res) {                                     
            console.log("ERROR:\n", res);
        }
    });
});