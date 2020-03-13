<template>
    <div id="portal-template">
        <img class="portal-logo" src="../assets/images/logo.png" alt="秒杀">
        <slot>

        </slot>
    </div>

</template>

<script>
    export default {
        name: 'portal-template',
        data() {
            return {}
        },

        components: {},
    }
</script>

<style lang="css">
    @import "../assets/css/portal-template.css";
</style>
