<template>
  <div class="container">
    <div class="header">
      <img src="../public/gov.svg" alt="image gov">
        <h1>O que você está procurando?</h1>
        <h5>Relatório CADOP 23/24</h5>
    </div>

    <div class="inputs">
      <input v-model="searchQuery" @input="filterItems" placeholder="Representante" />
      <input v-model="searchQuery" @input="filterItems" placeholder="Registro ANS" />
      <input v-model="searchQuery" @input="filterItems" placeholder="Nome Fantasia" />
      <input v-model="searchQuery" @input="filterItems" placeholder="Descrição" />
      <input v-model="searchQuery" @input="filterItems" placeholder="CEP" />
      <input v-model="searchQuery" @input="filterItems" placeholder="UF" />
    </div>

    <div class="queryResult">
      <ul>
        <li v-for="item in filteredItems" :key="item.id">{{ item.name }}</li>
      </ul>
    </div>
  </div>
</template>

<script>
export default {
  name: 'app',
  data() {
        return {
          searchQuery: '',
          items: [
            { id: 1, name: 'Maçã' },
            { id: 2, name: 'Banana' },
            { id: 3, name: 'Laranja' },
            { id: 4, name: 'Manga' },
          ],
          filteredItems: [],
        };
      },
      methods: {
        filterItems() {
          this.filteredItems = this.items.filter(item =>
            item.name.toLowerCase().includes(this.searchQuery.toLowerCase())
          );
        },
      },
      created() {
        this.filteredItems = [...this.items];
      }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #ffffff;
  margin-top: 60px;
}

body{
  background-color: #273848;
}

.inputs {

  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%); 
  width: 80%;

}

input {
  height: 35px;
  border-radius: 5px;
  border: 2px solid #2c3e50;
  margin: 2px;
}

img {
  width: 200px;
  margin: 50px;
}

.queryResult {
  margin: 200px;
}

ul {
  list-style-type: none;
}

@media (max-width:450px){
  .inputs {
    margin-top: 150px;
  }

  .queryResult {
    margin: 350px 0 0 0;
  }
}

@media (max-width:990px){
  .inputs {
   position: relative;
  }

  .queryResult {
   position: relative;
   margin: 0;
  }
}
</style>
