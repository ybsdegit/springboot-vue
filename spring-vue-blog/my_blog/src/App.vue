<template>
  <div id="app" class="main">
    <nav-bar/>
    <div class="main-container">
      <div class="left-container">
        <router-view/>
      </div>
      <right/>

    </div>
  </div>
</template>
<script>
  import NavBar from './views/navbar/navbar'
  import Right from './views/right/right'

  export default {
    components: {
      NavBar, Right
    },
    data() {
      return {
        current: ['1'],
      };
    },

  };
</script>

<style>
  .main {
    background-color: #f5f5f5;
  }
  .main-container {
    width: 1200px;
    margin: auto;
    display: flex;
    flex-direction: row;
  }

  .left-container {
    background-color: green;
    width: 850px;
    padding: 0 10px;
  }
</style>



