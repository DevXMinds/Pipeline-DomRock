function fetchBronzeData() {
    fetch('/arquivo/byEstagio/lz')
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
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
        div.addEventListener('click', function() { openModal(arquivo); });
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
    console.log(arquivo.pk);
    const delimiter = arquivo.delimiter;
    const rows = arquivo.dadosArquivo.split('\n');
    let html = '<tr>';

    const pkColumnIndex = (arquivo.lzs && arquivo.lzs.length > 0 && arquivo.lzs[0].colunaPk !== undefined) ? arquivo.lzs[0].colunaPk : -1;
    const headers = arquivo.header ? rows[0].split(delimiter).map(header => header.trim()) : [];
    // Handle the header separately if there is one
    if (arquivo.header) {
        rows[0].split(delimiter).forEach((header, index) => {
            const isPkColumn = index === pkColumnIndex;
            html += `<th${isPkColumn ? ' class="pk-column"' : ''}>${header.trim()}
                    ${!isPkColumn ? `<span class="close-col" onclick="deleteColumn(${index})">X</span>` : ''}
                    <select id="type-${index}" onchange="setType(${index})">
                        <option value="INT">INT</option>
                        <option value="DATE">DATE</option>
                        <option value="DATETIME">DATETIME</option>
                        <option value="VARCHAR">VARCHAR</option>
                        <option value="BINARY">BINARY</option>
                        <option value="BLOB">BLOB</option>
                    </select>
                 </th>`;
        });
        html += '</tr>';
    } else {
        rows.forEach((row, rowIndex) => {
            html += '<tr>';
            row.split(delimiter).forEach((cell, index) => {
                const isPkColumn = index === pkColumnIndex;
                html += `<td${isPkColumn ? ' class="pk-column"' : ''}>${cell.trim()}</td>`;
            });
            html += '</tr>';
        });
    }

    table.innerHTML = html;
    modal.style.display = "block";
    attachModalCloseHandlers(modal, span);

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

    // Collect selected types when confirming
    let selectedTypes = {};
    document.getElementById('confirmBtn').onclick = function() {
        const selects = document.querySelectorAll('select');
        selects.forEach(select => {
            const colIndex =  select.id.split('-')[1];
            selectedTypes[headers[colIndex]] = select.value;
        });
        const postData = {
            id: null,
            idUser: { id: arquivo.idUser.id},
            idArquivo: { id: arquivo.id }, //hardcoded
            dataModificacao: null, //hardcoded
            pk: arquivo.lzs.colunaPk, //hardcoded
            naodeletavel: arquivo.lzs.notdeletable, // hardcoded
            hash:null,
            tipagemBronze: JSON.stringify(selectedTypes),
        };

        console.log(postData)
        sendDataToBackend(postData)
    }

    function sendDataToBackend(data) {
        // You can use fetch or any other method to send data to the backend
        fetch('/bronze/load', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        })
            .then(response => {
                if (response.ok) {
                    console.log('Data sent successfully');
                    // Optionally, perform actions on successful data upload
                } else {
                    console.error('Failed to send data');
                }
            })
            .catch(error => {
                console.error('Error:', error);
            });
    }

    // Cancel button actions
    document.getElementById('cancelBtn').onclick = function() {
        console.log('Cancel button clicked');
        modal.style.display = "none";
        // Implement any additional logic needed on cancellation here
    };
}

function deleteColumn(colIndex) {
    const table = document.getElementById('csvTable');
    Array.from(table.rows).forEach(row => {
        if (row.cells[colIndex]) row.deleteCell(colIndex);
    });
}

function setType(colIndex, type) {
    const selectedType = document.getElementById(`type-${colIndex}`).value;
    console.log(`Type of column ${colIndex} set to ${selectedType}`);
}

function attachModalCloseHandlers(modal) {
    const span = modal.querySelector(".close");

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
}
function fetchUserData() {
    fetch('/arquivo/byEstagio/lz')
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            displayArquivos(data)})
        .catch(error => console.error('Error fetching data:', error));
}
