function addRow(id){
    var table = document.getElementById(id);
    var new_row = table.rows[1].cloneNode(true);
    var inp = new_row.cells[0].getElementsByTagName('input')[0];
    inp.value='';
    //inp.type='number';
   //inp.style.visibility='hidden';
    var inp1 = new_row.cells[1].getElementsByTagName('input')[0];
    inp1.value='';
    
    if(id == 'contacts') {
        var inp2 = new_row.cells[2].getElementsByTagName('input')[0];
        inp2.value='';
    }
    table.appendChild(new_row);
}

function getSelectedValue(rowid){
    document.getElementById("personId").value=document.getElementById("personId"+rowid).value;
    alert(rowid + "  "+ document.getElementById("personId"+rowid).value);
    document.forms[0].sumbit();
}

function deleteRow(row, table, formName, rowId) {
    var form = document.forms[formName];
    var i = row.parentNode.parentNode.rowIndex;
    var input = document.createElement('input');
    input.type = 'hidden';
    input.name = formName+"Deleted";
    input.value = rowId;
    form.appendChild(input);
    document.getElementById(table).deleteRow(i);
}

function detectChanges(formName) {
    var form = document.getElementById(formName);
	if (FormChanges(form).length > 0) {
	 // document.getElementById("changed").value = "yes";
	    return true;
	} else{
	    alert("No changes has been made");
	}
	return false;
}
