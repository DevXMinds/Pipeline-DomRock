document.getElementById('csvForm').addEventListener('submit', function (event) {
    event.preventDefault();

    document.getElementById('output').innerHTML = '';

    var delimiter = document.getElementById('delimiter').value;
    if (delimiter !== ',' && delimiter !== ';' && delimiter !== '|') {
        alert('Digite um delimitador válido (, ; |)');
        return;
    }

    var fileInput = document.getElementById('csvFile');
    var file = fileInput.files[0];
    if (!file) {
        alert('Por favor, selecione um arquivo CSV.');
        return;
    }

    var reader = new FileReader();
    reader.onload = function (event) {
        var csvData = event.target.result;

        var lines = csvData.split('\n');

        var columns = lines.map(function (line) {
            return line.split(',');
        });

        var output = document.getElementById('output');
        columns.forEach(function (row) {
            var newRow = row.join(delimiter); //
            var rowDiv = document.createElement('div');
            rowDiv.textContent = newRow;
            output.appendChild(rowDiv);
        });
    };

    reader.readAsText(file);
});
