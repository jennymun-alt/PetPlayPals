<template>
<div class='cards'>
    
    <article class='card'>
        <!-- {{date}} -->

        <br />
        <div class="textInCard">Available Date ⌚</div>{{date.dateOfDate}}
        <br />
        <br />
        <div class="textInCard">Address</div>{{date.address}}
        <br />
        <br />
        <div class="textInCard">City</div>{{date.city}} 
        <br />
        <br />
        <button v-show="!date.showPets" v-on:click.prevent="showAllPets(date)">Add a Pet</button>
        <div v-show="date.showPets" v-for="pet in allPets" v-bind:key="pet.petId">
            {{pet.petName}}
            <br />
            <button class="addAttendingBtn" v-show="!pet.attending" v-on:click.prevent="addPetToDate(pet,date)">Add Pet to This Date</button>
            <button class="removeAttendingBtn" v-show="pet.attending" v-on:click.prevent="removePetFromDate(pet,date)">Remove Pet From This Date</button>
        </div>
        <br />
        <button v-show="date.showPets" v-on:click.prevent="cancelShow(date)">Cancel</button>

        <p>Click a pet to view/rate ✔️💯</p>
        <div v-show="seeWho" v-bind:key="user.username" v-for="user in usersAttending">
             <!-- <p>Click a pet to view/rate</p> -->
             <hr class="divider" />
             
           <div class="attendee">Attendee: </div> {{user.username}}
           <li v-for="pet in user.userPets" v-bind:key="pet.petId" v-on:click.prevent="goTo(pet)">{{pet.petName}} is a: {{pet.breed}}, with {{pet.personality}} personality</li>

        </div>
        <button v-show="!seeWho" v-on:click.prevent="seeAttendance(date)">See Who's Attending</button>
        <button v-show="seeWho" v-on:click.prevent="seeAttendance(date)">Hide Attedance</button>

    </article>

</div>
  
</template>

<script>
import DateService from "../services/DateService";
import PetService from "../services/PetService";

export default {
    created(){
        
        DateService.getDateById(this.$route.params.dateId)
        .then((response)=>{
           
            this.date=response.data
            
        })
    },
    methods:{
        goTo(pet){
            //  path:"/pet/:petId",
            this.$router.push(`/pet/${pet.petId}`)

        },

        seeAttendance(date){
            this.seeWho=!this.seeWho;
            const usersHere={
                username: "",
                petList:{
                    petName: "",
                    breed: "",
                    personality: ""

                }
            }
            console.log(date)
            DateService.getUsersByDate(date.dateId).then(
                (response)=>{
                    console.log("this is the response "+response.data)
                    this.usersAttending=response.data;
                    console.log(this.usersAttending)
                    usersHere.username =this.usersAttending.username;
                    


                }

            )
            console.log("lets see attenting "+this.usersAttending)

        },

        removePetFromDate(pet,date){
            const ditchingPet={
                petId: pet.petId,
                dateId: date.dateId
            };
            console.log(ditchingPet.petId+" "+ditchingPet.dateId)
            DateService.removePetFromDate(ditchingPet).then(
                () =>{
                    
                }
            )
             this.$router.go()

        },
        addPetToDate(pet,date){
            console.log("petid " + pet.petId)
            console.log("date id" + date.dateId)
            const petDate ={
                petId: pet.petId,
                dateId: date.dateId
            }
            DateService.addPetToDate(petDate).then(
                (response)=>{
                    if(response.status===201){
                        window.alert("your Pet was Added to the date")
                          
                      }
                }
            )
            this.$router.go()


        },
        cancelShow(date){
            date.showPets=false

        },
        showAllPets(date){
            date.showPets= true
            console.log("hit method with user id:"+this.$store.state.user.id)
            
            PetService.getAllPets(this.$store.state.user.id).then(
                (response)=>{
                    this.allPets = response.data;
                   
                    for(let i =0;i<this.allPets.length;i++){
                        console.log(this.allPets[i].petId)
                       
                        for(let j =0;j<date.datingPets.length;j++){
                            console.log("inner for" +date.datingPets[j].petId)
                            
                            
                   
                            if(this.allPets[i].petId == date.datingPets[j].petId){
                                 this.allPets[i].attending = true;
                            }
                        }
                    }
                    
                }
            )
            
        
            for(let i=0; i<this.allDates.length;i++){
                if(this.allDates[i].dateId==date.dateId){
                    this.allDates[i].showPets =true;
                }
            }
           


        }
    },
    data(){
        return {
            seeWho: false,
            allDates: [],
            allPets: [],
            usersAttending: [],
            date: {
                city: "",
                playdate_id: "",
                address: "",
                dateofdate: "",
                datingPets:{
                    petId:""
                }
            }
        }
    }


    
}
</script>

<style scoped>
.cards {
  display: flex;
  justify-content: center;
  padding: 75px;
}

.card {
  display: inline-block;
  grid-template-rows: max-content 200px 1fr;

  border: 2px solid gray;
  border-radius: 15px;

  background-image: url('../assets/individualDate.jpg');
  background-repeat: no-repeat;
  background-size: cover;

  width: 57%;

  padding: 5px;
  margin: 30px;
  text-align: center;
  box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2);
  transition: 0.3s;
}

.card:hover {
  box-shadow: 0 8px 16px 0 rgba(0,0,0,0.2);
}

button {
  font-family: Tahoma, sans-serif;
  cursor:pointer;
	width:60%;
	border:none;
	background:rgb(146, 152, 158);
	color:#FFF;
	margin:0 0 5px;
	padding:10px;
	font-size:15px;
}

.textInCard {
    color: darkolivegreen;
}

.removeAttendingBtn {
    color: red;
}

.attendee {
    padding-top: 10px;
    padding-bottom: 10px;
    color: darkolivegreen;
    display: inline-block;
}

li {
    padding-top: 5px;
    padding-bottom: 20px;
    padding-left: 15px;
    text-align: left;
}

li:hover {
    color: teal;
}

.divider {
 border-top: 1px solid grey;
 margin-top: 25px; 
 margin-bottom: 25px;
 width: 75%
}

p {
    text-shadow: 0 0 3px #ee3434;
}

</style>