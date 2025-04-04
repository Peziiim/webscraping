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
    print("Conexão bem sucedida")
    return mydb

@app.route('/get-tables', methods=['POST', 'GET'])
def form():
    mydb = get_connection()
    mycursor = mydb.cursor()

    search_query = request.json.get('search_query', '').strip()

    query = """
    SELECT Registro_ANS, CNPJ, Nome_Fantasia, Modalidade, UF, CEP, Representante, 
           Data_Registro_ANS,
    FROM relatorio_cadop
    """


    filters = []
    if search_query:
        query += """
        WHERE Registro_ANS LIKE %s OR CNPJ LIKE %s OR Nome_Fantasia LIKE %s OR 
              Modalidade LIKE %s OR UF LIKE %s OR CEP LIKE %s OR 
              Representante LIKE %s OR Data_Registro_ANS LIKE %s
        """
        filters = [f'%{search_query}%']


    mycursor.execute(query, filters)
    result = mycursor.fetchall()

    items = [
        {
            'a.Registro_ANS': row[0], 
            'a.CNPJ': row[1],
            'a.Nome_Fantasia': row[2],
            'a.Modalidade': row[3], 
            'a.UF': row[4], 
            'a.CEP': row[5], 
            'a.Representante': row[6], 
            'a.Data_Registro_ANS': row[7], 
            'b.DESCRICAO': row[8],
            'VL_SALDO_INICIAL': row[9],
            'VL_SALDO_FINAL': row[10],
            'DIFERENCA': row[11]
        } for row in result
    ]

    mydb.close()

    response = {
    'searchQuery': search_query,
    'items': items,

   }

    if request.method == 'POST' or request.method == 'GET':
        return jsonify(response)
    
if __name__ == '__main__':
    app.run(debug=True)