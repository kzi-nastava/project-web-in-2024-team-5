<template>
    <div class="registerpage">
        <h1>Please enter credentials needed for registration.</h1>
        <form @submit.prevent="register">
            <input v-model="user.name" type="text" name="name" required placeholder="Enter Name">
            <br />
            <input v-model="user.lastname" type="text" name="lastname" required placeholder="Enter Last Name">
            <br />
            <input v-model="user.username" type="text" name="username" required placeholder="Enter Username">
            <br />
            <input v-model="user.password" type="password" name="password" required placeholder="Enter Password">
            <br />
            <input v-model="user.email" type="email" name="email" required placeholder="Enter Email">
            <br />
            <input v-model="user.phoneNumber" type="text" name="phoneNumber" required placeholder="Enter Phone Number">
            <br />
            <input v-model="user.role" type="text" name="role" required placeholder="Enter Role">
            <br />
            <input type="submit" value="Register" />


        </form>
    </div>
</template>

<script>
import axios from 'axios';
export default {
    data() {
        return {
            user: {
                name: '',
                lastname: '',
                username: '',
                password: '',
                email: '',
                phoneNumber: '',
                role: '',
            }
        }
    },
    methods: {
        async register() {
                try {
                    const response = await axios.post('http://localhost:8080/api/v1/register', this.user, {
                        headers: {
                            'Content-Type': 'application/json' // Set content type to JSON
                        }
                    });
                    if (response.status === 200) {
                        this.$router.push('/settings');
                    }
                    else {
                        alert('Registration failed');
                    }
                }
                catch (error) {
                    if (error.response && error.response.status === 403) {
                        alert("You're already logged in");
                    } else {
                        alert("Registration failed");
                    }
                }
        }
    }

}
</script>

<style scoped></style>