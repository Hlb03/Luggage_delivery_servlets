function PassCheck() {
    var password = document.getElementById('pas1');
    var vpassword = document.getElementById('pas2');

    document.getElementById("registrateBtn").disabled = password.value.length === 0 ||
        password.value != vpassword.value;
}