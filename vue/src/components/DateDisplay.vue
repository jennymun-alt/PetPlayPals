<template>
    <div class="container">

      <form v-on:submit.prevent="addDate" class="addPalForm">
      <h3>Add a Date!</h3>
      <h4>Search a date and schedule a Playdate!</h4>

      <!-- city, address, date of Date -->
      <div class="form-group">
        <input id="city" placeholder="city" type="text" v-model="date.city" />
      </div>

      <div class="form-group">
        <input id="address" placeholder="address" type="text" v-model="date.address" />
      </div>

      <div class="form-group">
        <input id="dateOfDate" placeholder="dateOfDate" type="date" v-model="date.dateOfDate" />
      </div>

      <!-- <div class="form-group">
        <input id="time" placeholder="time" type="time" />
      </div> -->

      <button class="btn btn=submit" id="submitButton">Add a Playdate</button>
      <button
        class="btn btn-cancel"
        v-on:click.prevent="cancelForm"
        type="cancel"
      >
        Cancel
      </button>
    </form>

    </div>
</template>

<script>
import DateService from "../services/DateService";

export default {
  components: { },
  data(){
      return{
          date:{
              city: "",
              address: "",
              dateOfDate: ""
          },
          addingDate:false,
          allDates:[]
      }
  },
  methods:{
      showForms(){
          this.addingDate=!this.addingDate;

      },
      
      addDate(){
          const newDate ={
              city: this.date.city,
              address: this.date.address,
              dateOfDate: this.date.dateOfDate
          };

          console.log(newDate);
          if(!newDate.city==""&& !newDate.address=="" && !newDate.dateOfDate==""){
                    if(newDate!=null){
              DateService.addDate(newDate).then(
                  (response)=>{
                      if(response.status===201){
                          //do stuff
                      }
                  }
              )
          }
                    this.cancelForm();
          this.$router.go()

          } else{
            alert("Please fill all forms");
          }

  

      },
        cancelForm() {
      this.date.city = "";
      this.date.address = "";
      this.date.dateOfDate = "";
    },
  }  

}
</script>

<style scoped>
#dateOfDate {
    margin-bottom: 10px;
}

.container {
    padding: 110px;
}
</style>