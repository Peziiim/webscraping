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
    return mydb

@app.route('/', methods=['POST'])
def form():
    mydb = get_connection()
    mycursor = mydb.cursor()

    query = """
    SELECT a.Registro_ANS, a.CNPJ, a.Nome_Fantasia, a.Modalidade, a.UF, a.CEP, a.Representante, 
           a.Data_Registro_ANS, b.DESCRICAO, b.VL_SALDO_INICIAL, b.VL_SALDO_FINAL, 
           (b.VL_SALDO_INICIAL - b.VL_SALDO_FINAL) AS DIFERENCA
    FROM relatorio_cadop a
    JOIN all_tables b ON a.REGISTRO_ANS = b.REG_ANS
    """

    mycursor.execute(query)
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
            'b.DESCRICAO': row[8]
        } for row in result
    ]

    mydb.close()

    response = {
        'searchQuery': request.form.get('nome_busca', '') if request.method == 'POST' else '',
        'items': items,
        'filteredItems': items  
    }

    if request.method == 'POST':
        return jsonify(response)