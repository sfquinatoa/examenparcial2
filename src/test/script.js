const URL_EVENTO = "http://localhost:8080/api/evento";
const URL_PONENTE = "http://localhost:8080/api/ponente";

window.onload = function () {
    cargarPonentes();
    listarEventos();
};

function cargarPonentes() {

    fetch(URL_PONENTE)
        .then(res => res.json())
        .then(data => {

            console.log("Ponentes recibidos:", data);

            const select = document.getElementById("ponente");

            select.innerHTML = '<option value="">Seleccione un ponente</option>';

            if (!data || data.length === 0) {
                console.log("No hay ponentes en la BD");
                return;
            }

            data.forEach(p => {
                select.innerHTML += `
                    <option value="${p.id}">
                        ${p.nombre}
                    </option>
                `;
            });

        })
        .catch(err => {
            console.error("Error cargando ponentes:", err);
        });
}

// ================= GUARDAR =================
function guardarEvento() {

    const idPonente = document.getElementById("ponente").value;

    if (!idPonente) {
        alert("Debes seleccionar un ponente");
        return;
    }

    const evento = {
        nombre: document.getElementById("nombre").value,
        tipo: document.getElementById("tipo").value,
        fecha: document.getElementById("fecha").value,
        capacidad: parseInt(document.getElementById("capacidad").value),
        ponente: {
            id: parseInt(idPonente)
        }
    };

    console.log("Enviando:", evento);

    fetch(URL_EVENTO, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(evento)
    })
    .then(res => {
        if (!res.ok) {
            throw new Error("Error al guardar evento");
        }
        return res.json();
    })
    .then(() => {
        alert("Evento guardado");
        listarEventos();
        limpiar();
    })
    .catch(err => {
        console.error(err);
        alert("No se pudo guardar el evento");
    });
}


function listarEventos() {

    fetch(URL_EVENTO)
        .then(res => res.json())
        .then(data => {

            const tabla = document.getElementById("tablaEventos");
            tabla.innerHTML = "";

            data.forEach(e => {
                tabla.innerHTML += `
                    <tr>
                        <td>${e.id}</td>
                        <td>${e.nombre}</td>
                        <td>${e.tipo}</td>
                        <td>${e.fecha}</td>
                        <td>${e.capacidad}</td>
                        <td>${e.ponente?.nombre || ''}</td>
                        <td>
                            <button class="btn btn-danger btn-sm" onclick="eliminar(${e.id})">
                                Eliminar
                            </button>
                        </td>
                    </tr>
                `;
            });

        });
}

// ================= ELIMINAR =================
function eliminar(id) {

    fetch(`${URL_EVENTO}/${id}`, {
        method: "DELETE"
    })
    .then(() => {
        mostrarAlerta("Evento eliminado", "warning");
        listarEventos();
    });
}

// ================= LIMPIAR =================
function limpiar() {
    document.getElementById("nombre").value = "";
    document.getElementById("tipo").value = "";
    document.getElementById("fecha").value = "";
    document.getElementById("capacidad").value = "";
    document.getElementById("ponente").value = "";
}

// ================= ALERTAS =================
function mostrarAlerta(mensaje, tipo) {

    const alerta = document.createElement("div");

    alerta.className = `alert alert-${tipo} position-fixed top-0 end-0 m-3`;

    alerta.style.zIndex = "9999";
    alerta.innerText = mensaje;

    document.body.appendChild(alerta);

    setTimeout(() => {
        alerta.remove();
    }, 2000);
}