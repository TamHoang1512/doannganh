

/* global fetch */

function delNews(endpoint, id) {
    let d = document.getElementById("load" + id);
    d.style.display = "block";
    fetch(endpoint, {
        method: "delete"
    }).then(function (res) {
        if (res.status === 204)
            location.reload();
    }).catch(function (err) {
        console.error(err);
    });
}

function loadAdminNewssssss(endpoint) {
    fetch(endpoint).then(function (data) {
        let msg = "";
        for (let i = 0; i < 10; i++) {
            msg += `
            <h2>hihi</h2>
            `;

        }

        let d = document.getElementById("adminNewsne");
        d.innerHTML = msg;

        let d2 = document.getElementById("myLoading");
        d2.style.display = "none";
    });
}