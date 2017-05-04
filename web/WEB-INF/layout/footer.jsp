

<!-- #### FIN CONTENU PAGE #### -->
</div>

<!-- #### FIN CONTAINER #### -->

</div>

<!--CHARGMT SCRIPTS-->

<script>
    $('.feedback').delay(300).fadeIn("slow");

//   JQuery QCM 
    $(document).ready(function(){

    $("#addRep").on('click', function(){
    var valRep = $('#liste-question > div').length + 1;
            if (valRep < 7) {
    var newRep = "<div class='input-group' style='margin-top: 15px;'>"
            + "<input type='text' class='form-control' id='reponse' name='reponse[" + valRep + "]'>"
            + "<span class='input-group-addon'><input type='checkbox' name='correcte[" + valRep + "]'></span>"
            + "</div>";
            $('#liste-question > div:last').after(newRep);
    }
    });
    
    $("#delRep").on('click',function(){
        var countRep = $('#liste-question > div').length;
        
        if(countRep>2){
            $('#liste-question > div:last').remove();
        }
    });
    
    //row table clickable
    $(".clickable-row").click(function() {
        window.location = $(this).data("href");    
    });
    
    //On change formation or etat submit form candidature
    $('#candidatures select, .input-group.date').change(function(){
        $('#candidatures').submit();    
    });
    
    $("#recherche").keypress(function(event) {
        if (event.which == 13) {
            $('#candidatures').submit();
        }
    });
});
//datepicker
    $('.input-group.date').datepicker({
        format: "dd/mm/yyyy",
        language: "fr",
        todayHighlight: true,
        clearBtn: true,
        autoclose: true,
        toggleActive: true
    });
    
    $('.input-group.date').datepicker()
        .on(picker_event, function(e) {
        $('#candidatures').submit();
    });
</script>

<!-- FIN CHARGEMENT SCRIPT-->
</body>

</html>
