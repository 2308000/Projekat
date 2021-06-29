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
        localStorage.setItem("role", "odjavljen");
        window.location.href = "../index.html";
    }
    var zastita = uloga;
    var id = localStorage.getItem("id");

    var provera = {
        zastita,
        id
    }

    $.ajax({
        type: "POST",                                              
        url: "http://localhost:8080/api/clan/profil",                 
        dataType: "json",  
        contentType: "application/json",                            // tip podataka koje šaljemo
        data: JSON.stringify(provera),                                              
        success: function (res) { 
            
            if(res.zastita !== "clan") {
                alert("Nemate pristup ovoj stranici!");
                localStorage.setItem("role", "odjavljen");
                window.location.href = "../index.html";
            }

            let temp1 = res.datumRodjenja;
            temp1 = temp1.substring(0,10) + ' ' + temp1.substring(11, 16);

            let c1 = "<td class=\"newCell center\">" + res.id + "</td>";       
            let c2 = "<td class=\"newCell center\">" + res.korisnickoIme + "</td>";        
            let c3 = "<td class=\"newCell center\">" + res.ime + "</td>";   
            let c4 = "<td class=\"newCell center\">" + res.prezime + "</td>";       
            let c5 = "<td class=\"newCell center\">" + res.password + "</td>";   
            let c6 = "<td class=\"newCell center\">" + res.email + "</td>";   
            let c7 = "<td class=\"newCell center\">" + temp1 + "</td>";       
            let c8 = "<td class=\"newCell center\">" + res.telefon + "</td>";        
            let c9 = "<td class=\"newCell center\">" + res.ocene + "</td>";   
            let c10 = "<td class=\"newCell center\">" + res.odradjeniTermini + "</td>";       
            let c11 = "<td class=\"newCell center\">" + res.ocenjeniTermini + "</td>";  
            $('#id-clana').append(c1);      
            $('#user').append(c2);  
            $('#ime').append(c3);  
            $('#prezime').append(c4);  
            $('#pass').append(c5);  
            $('#email').append(c6); 
            $('#datumRodjenja').append(c7);      
            $('#telefon').append(c8);  
            $('#ocene').append(c9);  
            $('#odradjeni').append(c10);  
            $('#ocenjeni').append(c11);   
        },
        error: function (res) {                                     
            console.log("ERROR:\n", res);
        }
    });
});

function logout() {
    console.log("Logged out successfully!");
    localStorage.setItem("role", "odjavljen");
}

function logout() {
    console.log("Logged out successfully!");
    localStorage.setItem("role", "odjavljen");
}