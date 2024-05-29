<template>
    <div>
        <h1>
            Here you can login!
        </h1>
        <form @submit.prevent="login">
            <input v-model="user.username" type="text" name="user.username" required="true" placeholder="Input username">
            <input v-model="user.password" type="password" name="user.password" required="true" placeholder="Input password">

        </form>
        <button @click="login">Login</button>
    </div>
</template>

<script>
import axios from 'axios';
export default {
    data() {
        return {
            user: {
                username: '',
                password: ''
            }
        }
    },
    methods: {
        async login() {
            try {
                const response = await axios.post("http://localhost:8080/api/v1/login", this.user, {
                    headers: {
                        "Content-Type": 'application/json'
                    }
                });
                if (response.status === 200) {
                    localStorage.setItem('user', JSON.stringify(response.data));
                    this.$router.push('/about');
                    alert("Successfully logged in!")
                } if (response.status === 401) {
                    alert("User doesn't exist!")
                }
                console.log(response.data);
            }
            catch (error) {
                if(response.status === 401) {
                alert("User doesn't exist!")
                }
                console.error(error);
            }

        }
    }
}
</script>

<style></style>