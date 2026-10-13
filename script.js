/* script.js - fetches XML using XMLHttpRequest and shows it without reloading the page */

var loadBtn = document.getElementById('loadBtn');
var messageBox = document.getElementById('message');
var tableBody = document.getElementById('tableBody');
var resultTable = document.getElementById('resultTable');

function showMessage(text, type) {
    messageBox.textContent = text;
    messageBox.className = type;      // "loading" or "error"
}

function hideMessage() {
    messageBox.textContent = '';
    messageBox.className = '';
}

/** Reads the text of a child tag, e.g. <name>Arun</name> */
function getText(parent, tagName) {
    var node = parent.getElementsByTagName(tagName)[0];
    return node ? node.textContent : '';
}

/** Builds table rows from the XML document. */
function displayStudents(xmlDoc) {
    var students = xmlDoc.getElementsByTagName('student');
    tableBody.innerHTML = '';

    if (students.length === 0) {
        showMessage('The XML file has no student records.', 'error');
        resultTable.style.display = 'none';
        return;
    }

    for (var i = 0; i < students.length; i++) {
        var row = document.createElement('tr');
        var fields = ['id', 'name', 'department', 'year'];
        for (var j = 0; j < fields.length; j++) {
            var cell = document.createElement('td');
            cell.textContent = getText(students[i], fields[j]);   // textContent keeps output safe
            row.appendChild(cell);
        }
        tableBody.appendChild(row);
    }
    resultTable.style.display = 'table';
    hideMessage();
}

function loadStudents() {
    var xhr = new XMLHttpRequest();

    showMessage('Loading data, please wait...', 'loading');
    resultTable.style.display = 'none';
    loadBtn.disabled = true;

    xhr.open('GET', 'get_students.php?delay=1&t=' + new Date().getTime(), true); // true = asynchronous

    xhr.onreadystatechange = function () {
        if (xhr.readyState !== 4) {
            return; // request not finished yet
        }
        loadBtn.disabled = false;

        if (xhr.status === 200) {
            var xmlDoc = xhr.responseXML;
            if (!xmlDoc || xmlDoc.getElementsByTagName('parsererror').length > 0) {
                showMessage('Error: the server sent invalid XML.', 'error');
            } else {
                displayStudents(xmlDoc);
            }
        } else if (xhr.status === 0) {
            showMessage('Error: could not reach the server. Is Apache running?', 'error');
        } else {
            showMessage('Error ' + xhr.status + ': ' + (xhr.responseText || xhr.statusText), 'error');
        }
    };

    xhr.onerror = function () {
        loadBtn.disabled = false;
        showMessage('Network error: request failed.', 'error');
    };

    xhr.send();
}

loadBtn.addEventListener('click', loadStudents);
