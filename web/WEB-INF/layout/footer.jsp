

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
            $("#delRep").on('click', function(){
    var countRep = $('#liste-question > div').length;
            if (countRep > 2){
    $('#liste-question > div:last').remove();
    }
    });
            $(".clickable-row").click(function() {
    window.location = $(this).data("href");
    });
            });
//datepicker
            $('.input-group.date').datepicker({
    format: "dd/mm/yyyy",
            todayBtn: true,
            language: "fr",
            todayHighlight: true
            });
</script>

<!-- FIN CHARGEMENT SCRIPT-->
</body>

</html>
