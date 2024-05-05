function fetchBronzeData() {
    fetch('/arquivo/byEstagio/lz')
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            console.log(data)
            displayArquivos(data)})
        .catch(error => console.error('Error fetching data:', error));
}

// Function to display data on the page
function displayArquivos(arquivos) {
    const container = document.getElementById('arquivo-container');
    container.innerHTML = ''; // Clear previous contents
    arquivos.forEach(arquivo => {
        const div = document.createElement('div');
        div.className = 'arquivo-entry';
        div.innerHTML = `
            <p><strong>Nome do Upload:</strong> ${arquivo.nomeUpload}</p>
            <p><strong>Estágio:</strong> ${arquivo.estagio}</p>
        `;
        div.onclick = function() { openModal(arquivo); }
        container.appendChild(div);
    });
}

document.addEventListener('DOMContentLoaded', function() {
    const middleIcon = document.getElementById('middle-icon');
    if (middleIcon) {
        middleIcon.addEventListener('click', fetchBronzeData);
    }
});

function openModal(arquivo) {
    const modal = document.getElementById('myModal');
    const span = document.getElementsByClassName("close")[0];
    const table = document.getElementById('csvTable');
    table.innerHTML = ''; // Clear previous table contents

    // Get the correct delimiter
    const delimiter = arquivo.delimiter;

    // Parse CSV data using the provided delimiter
    const rows = arquivo.dadosArquivo.split('\n');
    let html = '<tr>';

    // Handle the header separately if there is one
    if (arquivo.header) {
        rows[0].split(delimiter).forEach(header => {
            html += `<th contenteditable='true'>${header.trim()}</th>`; // Use trim to clean any whitespace
        });
        html += '</tr>';
        rows.slice(1).forEach(row => {
            html += '<tr>';
            row.split(delimiter).forEach(cell => {
                html += `<td>${cell.trim()}</td>`; // Use trim to clean any whitespace
            });
            html += '</tr>';
        });
    } else {
        // If no header, create an empty header row
        rows[0].split(delimiter).forEach(() => {
            html += '<th contenteditable="true"></th>';
        });
        html += '</tr>';
        rows.forEach(row => {
            html += '<tr>';
            row.split(delimiter).forEach(cell => {
                html += `<td>${cell.trim()}</td>`;
            });
            html += '</tr>';
        });
    }

    table.innerHTML = html;
    modal.style.display = "block";

    // Close modal when X is clicked
    span.onclick = function() {
        modal.style.display = "none";
    }

    // Close modal when clicking outside of it
    window.onclick = function(event) {
        if (event.target === modal) {
            modal.style.display = "none";
        }
    }

    // Confirm button actions
    document.getElementById('confirmBtn').onclick = function() {
        console.log('Confirm button clicked');
        modal.style.display = "none";
        // Implement any additional logic needed on confirmation here
    };

    // Cancel button actions
    document.getElementById('cancelBtn').onclick = function() {
        console.log('Cancel button clicked');
        modal.style.display = "none";
        // Implement any additional logic needed on cancellation here
    };
}

