$(document).ready(function () { 
    var uloga = localStorage.getItem("role");
    if(uloga === "admin") {
        window.location.href = "admin.html";
    }
    if(uloga === "trener") {
        window.location.href = "trener.html";
    }
    if(uloga === null) {
        window.location.href = "../index.html";
    }
});


function logout() {
    console.log("Logged out successfully!");
    localStorage.setItem("role", null);
}