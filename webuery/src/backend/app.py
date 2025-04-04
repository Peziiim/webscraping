#Para rodar o codigo, execute o comando flask run no diretorio webuery/src/backend e rode o npm run serve do vue

from flask import Flask, request, jsonify
from flask_cors import CORS
import mysql.connector

app = Flask(__name__)
CORS(app, resources={r"/get-tables": {"origins": "*"}})

def get_connection():
    mydb = mysql.connector.connect(
        host='localhost',
        user='root',
        password='Sete45082526.',
        database='relatorios'
    )
    
    return mydb

@app.route('/get-tables', methods=['POST'])
def form():
    mydb = get_connection()
    mycursor = mydb.cursor()

    search_query = request.json.get('search_query', '').strip()

    print(f"Search query: '{search_query}'")  
    
    if search_query:
        query = """
        SELECT Registro_ANS, CNPJ, Nome_Fantasia, Modalidade, UF, CEP, Representante,
               Data_Registro_ANS, Razao_Social
        FROM relatorio_cadop
        WHERE Registro_ANS LIKE %s OR CNPJ LIKE %s OR Nome_Fantasia LIKE %s OR
              Modalidade LIKE %s OR UF LIKE %s OR CEP LIKE %s OR
              Representante LIKE %s OR Data_Registro_ANS LIKE %s OR Razao_Social LIKE %s
        """
        
        search_param = f"%{search_query}%"
        params = [search_param] * 9
        
        print(f"Executing query with params: {params}")  
        mycursor.execute(query, params)
    else:
        query = """
        SELECT Registro_ANS, CNPJ, Nome_Fantasia, Modalidade, UF, CEP, Representante,
               Data_Registro_ANS, Razao_Social
        FROM relatorio_cadop
        """
        print("Executing query without search filters")  
        mycursor.execute(query)
    
    result = mycursor.fetchall()
    print(f"Found {len(result)} results") 

    items = [
        {
            'Registro_ANS': row[0], 
            'CNPJ': row[1],
            'Nome_Fantasia': row[2],
            'Modalidade': row[3], 
            'UF': row[4], 
            'CEP': row[5], 
            'Representante': row[6], 
            'Data_Registro_ANS': row[7],
            'Razao_Social': row[8],
        } for row in result
    ]

    mydb.close()

    response = {
    'searchQuery': search_query,
    'items': items,

   }

    if request.method == 'POST' or request.method == 'GET':
        #Para verificar o resultado através do botão da interface web pelo terminal
        print(f"Response: {response}")
        return jsonify(response)
    
if __name__ == '__main__':
    app.run(debug=True)