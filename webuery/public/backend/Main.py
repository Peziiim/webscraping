from flask import Flask, request, jsonify
import mysql.connector

app = Flask(__name__)

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
    SELECT a.Registro_ANS, a.CNPJ, a.Nome_Fantasia, a.Modalidade, a.UF, a.CEP, a.Representante, 
           a.Data_Registro_ANS, b.DESCRICAO, b.VL_SALDO_INICIAL, b.VL_SALDO_FINAL, 
           (b.VL_SALDO_INICIAL - b.VL_SALDO_FINAL) AS DIFERENCA
    FROM relatorio_cadop a
    JOIN _1t2023 b ON a.REGISTRO_ANS = b.REG_ANS
    """


    filters = []
    if search_query:
        query += " WHERE a.Registro_ANS LIKE %s"
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
    'items': items,
    'searchQuery': search_query,

   }

    if request.method == 'POST' or request.method == 'GET':
        return jsonify(response)