<template>
    <div>
        <h1>
           Here you can log out! 
        </h1>
        <button @click = "logout">Logout</button>
    </div>
</template>

<script>
    import axios from 'axios';
    import EventBus from './event'
    export default {
        
        methods:{
        async logout() {
            try {
                const response = await axios.post("http://localhost:8080/api/v1/logout");
                this.$router.push('/login');
                EventBus.$emit('userLoggedOut');
                console.log(response.data);
            }
            catch(error) {
                console.error(error);
            }

        }
     }
    }
</script>

<style>

</style>