document.getElementById('csvForm').addEventListener('submit', function (event) {
    event.preventDefault();

    document.getElementById('output').innerHTML = '';

    var fileInput = document.getElementById('csvFile');
    var file = fileInput.files[0];
    if (!file) {
        alert('Por favor, selecione um arquivo CSV.');
        return;
    }

    var reader = new FileReader();
    reader.onload = function (event) {
        var csvData = event.target.result;

        var delimiter = detectDelimiter(csvData);
        if (!delimiter) {
            alert('Não foi possível detectar o delimitador padrão do CSV.');
            return;
        }

        var userDelimiter = document.getElementById('delimiter').value;

        var modifiedCsvData = csvData.replace(new RegExp(escapeRegExp(delimiter), 'g'), userDelimiter);

        var lines = modifiedCsvData.split('\n');

        var output = document.getElementById('output');
        lines.forEach(function (line) {
            var rowDiv = document.createElement('div');
            rowDiv.textContent = line;
            output.appendChild(rowDiv);
        });
    };

    reader.readAsText(file);
});

function detectDelimiter(csvData) {
    var delimiters = [',', ';', '|'];
    for (var i = 0; i < delimiters.length; i++) {
        if (csvData.indexOf(delimiters[i]) !== -1) {
            return delimiters[i];
        }
    }
    return null;
}


function escapeRegExp(string) {
    return string.replace(/[.*+?^${}()|[\]\\]/g, '\\$&');
}
