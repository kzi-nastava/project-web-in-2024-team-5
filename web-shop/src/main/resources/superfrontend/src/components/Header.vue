<template>
  <header>
      <div class = "wrapper">
    <nav class = "flex flex-row justify-around">
      <RouterLink to = "/">Home</RouterLink>
      <RouterLink to = "/products">Products</RouterLink>
      <RouterLink to = "/about">About us</RouterLink>
      <RouterLink to = "/logout">Logout</RouterLink>
      <RouterLink to = "/login">Login</RouterLink>
      <RouterLink to = "/register">Register</RouterLink>
    </nav>
  </div>
  </header>
  <RouterView />
</template>
<style scoped>
nav {
  display: flex;

  background-color: lightblue;
  width: 100%;
  font-size: 20px;
  text-align: left;
}
nav {
    border: 1px solid black;
} 
nav a {
  text-decoration: none;
  margin-right: 3rem;
  color: black;
}
nav a:hover{
  color: gray;
}
h1{
    display: flex;
    justify-content: center;
}
form {
    display: flex;
    flex-direction: column;
    width: 20%;
    margin: 0px auto;
}
button {
    display: flex;
    margin: 0px auto;
}
</style>




<script>

</script>