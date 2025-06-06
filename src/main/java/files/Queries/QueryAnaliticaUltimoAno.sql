WITH eventos AS (
    SELECT REG_ANS, DESCRICAO, VL_SALDO_INICIAL, VL_SALDO_FINAL 
    FROM _1t2024
    UNION ALL
    SELECT  REG_ANS, DESCRICAO, VL_SALDO_INICIAL, VL_SALDO_FINAL 
    FROM _2t2024
    UNION ALL
	SELECT  REG_ANS, DESCRICAO, VL_SALDO_INICIAL, VL_SALDO_FINAL 
    FROM _3t2024
    UNION ALL
       SELECT  REG_ANS, DESCRICAO, VL_SALDO_INICIAL, VL_SALDO_FINAL 
    FROM _4t2024
)
SELECT  a.REGISTRO_ANS, a.Nome_Fantasia, b.DESCRICAO, b.REG_ANS, b.DESCRICAO, b.VL_SALDO_INICIAL, b.VL_SALDO_FINAL,
       (b.VL_SALDO_INICIAL - b.VL_SALDO_FINAL) AS DIFERENCA
FROM eventos b
JOIN relatorio_cadop a ON a.Registro_ANS = b.REG_ANS
WHERE DESCRICAO = 'EVENTOS/ SINISTROS CONHECIDOS OU AVISADOS  DE ASSISTÊNCIA A SAÚDE MEDICO HOSPITALAR '
ORDER BY DIFERENCA DESC
LIMIT 10;