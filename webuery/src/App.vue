<template>
  <div class="container">
    <div class="header">
      <img src="./assets/gov.png" alt="image gov">
      <h1>O que você está procurando?</h1>
      <h5>Relatório CADOP 23/24</h5>
    </div>
    
    <div class="inputs" action="/get-tables" method="post">
      <input 
        v-model="searchQuery" 
        placeholder="Digite aqui" 
        class="search-input"
        id="form" 
      />
      <button @click.prevent="btnGet" class="search-button">Buscar</button>
    </div>
    </div>
</template>
<script>
  import axios from 'axios';

export default {
  name: 'App',
  data() {
    return {
      searchQuery: '',
      items: []
    };
  },

  methods: {

    async getFunction() {
      try{
        const response = await axios.post('http://127.0.0.1:5000/get-tables', {
              searchQuery: this.searchQuery
            
          });

          this.items = response.data.items || [];

        } catch (error) {
          console.error('Erro buscando os dados:', error);
          this.items = []; 
        }
      },

        btnGet() {
        this.getFunction();
        console.log(this.searchQuery);
     }
  }      
}

</script>

<style>
body {
  background-color: #3d4766;
}
.container {
  margin: 0 auto;
  padding: 20px;
  font-family: Arial, sans-serif;
  display: flex;
  flex-direction: column;
  align-items: center;
}

.header {
  text-align: center;
  margin-bottom: 30px;
}

.header img {
  max-width: 200px;
}

.inputs {
  display: flex;
  justify-content: center;
  margin-bottom: 20px;
}

.search-input {
  padding: 10px;
  width: 300px;
  border: 1px solid #ccc;
  border-radius: 4px;
  margin-right: 10px;
}

.search-button {
  padding: 10px 20px;
  background-color: #4CAF50;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

.search-button:hover {
  background-color: #45a049;
}

.loading, .error, .no-results {
  text-align: center;
  padding: 20px 20px 0 0;
  font-size: 16px;
}

.error {
  color: red;
}

.results-table {
  width: 90%; 
  background-color: white;
  
}

.results-table th, .results-table tr{
  border-right: 1px solid grey;
  height: 100%;
}

#first {
  border-left: 1px solid grey;
}

</style>