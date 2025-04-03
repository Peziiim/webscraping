<template>
  <div class="container">
    <div class="header">
      <img src="../public/gov.png" alt="image gov">
      <h1>O que você está procurando?</h1>
      <h5>Relatório CADOP 23/24</h5>
    </div>
    
    <div class="inputs">
      <input 
        v-model="searchQuery" 
        @input="debounceSearch" 
        placeholder="Registro ANS" 
        class="search-input"
      />
      <button @click="fetchData" class="search-button">Buscar</button>
    </div>
      
      <table class="results-table">
        <thead>
          <tr>
            <th>Registro ANS</th>
            <th>CNPJ</th>
            <th>Nome Fantasia</th>
            <th>Modalidade</th>
            <th>UF</th>
            <th>Descrição</th>
            <th>Saldo Inicial</th>
            <th>Saldo Final</th>
            <th>Diferença</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="item in items" :key="item['a.Registro_ANS']">
            <td>{{ item['a.Registro_ANS'] }}</td>
            <td>{{ item['a.CNPJ'] }}</td>
            <td>{{ item['a.Nome_Fantasia'] }}</td>
            <td>{{ item['a.Modalidade'] }}</td>
            <td>{{ item['a.UF'] }}</td>
            <td>{{ item['b.DESCRICAO'] }}</td>
            <td>{{ item['VL_SALDO_INICIAL'] }}</td>
            <td>{{ item['VL_SALDO_FINAL'] }}</td>
            <td>{{ item['DIFERENCA'] }}</td>
          </tr>
        </tbody>
      </table>
    </div>
</template>
<script>


export default {
  name: 'App',
  
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
  border-right: 3px solid grey;
  height: 100%;
}

</style>