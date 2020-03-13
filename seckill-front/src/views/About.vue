<template>
  <div class="about">
    <img src="../assets/logo.png" alt="logo">
    <h1>{{msg}}</h1>
    <h2>{{code}}</h2>
    <input type="button" :value="code">
    <input type="text" v-model="code">
    <button @click="changeMsg">clike me</button>
    <br>

    firstname<input type="text" v-model="firstname"> <br>
    lastname<input type="text" v-model="lastname"> <br>

    fullname : {{firstname}} &nbsp; {{lastname}} <br>
    fullname method: {{getFullName()}}<br>
    fullname computed:  {{fullname}}<br>
    <br>
  </div>
</template>


<script>
  export default {
    computed: {
      fullname: function() {
        return this.firstname + ' ' + this.lastname;
      }
    },
    methods: {
      changeMsg(){
        this.msg = this.firstname
      },
      getFullName(){
        return this.firstname + ' ' + this.lastname;
      }
    },
    name: "about",
    data(){
      return {
        msg: "Welcome to Your Vue.js App",
        code: 1234567890,
        firstname: 'andy',
        lastname: '朕',
      }
    }
  }
</script>
