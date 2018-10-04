DROP PROCEDURE IF EXISTS FILTRO_ANUNCIO

DELIMITER //

CREATE PROCEDURE FILTRO_ANUNCIO (
  IN IMOVEL    BOOL,
  IN MOVEL     BOOL,
  IN MATERIAL  BOOL,
  IN PETS      BOOL,
  IN MIN_VALOR DOUBLE,
  IN MAX_VALOR DOUBLE,
  IN ORDENACAO ENUM('0', '1', '2', '3')
)
BEGIN
    -- Retorna os anuncios com base nos filtros informados.
    -- A expressão: 'IF (IFNULL(MOVEL, FALSE) = TRUE, 1, NULL)' verifica se o valor do filtro foi informado ou se é nulo, se for nulo retorna o valor FALSE para fazer a comparação
    -- Caso a comparação com o valor do filtro retorne FALSE a consulta retornará sem olhar o campo comparado por conta do 'TRUE OR...' do começo da expressão
    -- O comando 'GROUP BY ANUNCIO.ID_ANUNCIO' impede que seja retornado uma linha para cada foto vinculada ao anúncio
    -- Valores possíveis para a variável ORDENACAO: 1 - Maior valor, 2 - Menor valor e 3 - Título anúncio (alfabetica)
    
    SELECT DISTINCT
      ID_ANUNCIO,
      DS_TITULO,
      ANUNCIO.DS_DESCRICAO,
      NR_VALOR,
      CAT_ANUNCIO.DS_DESCRICAO AS DS_CATEGORIA,
      FOTO.DS_CAMINHO AS DS_CAMINHO
    FROM
      TB_ANUNCIO AS ANUNCIO
      LEFT JOIN
        TB_CATEGORIA_ANUNCIO AS CAT_ANUNCIO ON CAT_ANUNCIO.ID_CATEGORIA = ANUNCIO.TB_CATEGORIA_ID_CATEGORIA
      LEFT JOIN
        TB_FOTOS AS FOTO ON FOTO.TB_ANUNCIO_ID_ANUNCIO = ANUNCIO.ID_ANUNCIO
    WHERE
      TB_STATUS_ID_STATUS = 2
    AND
      ANUNCIO.TB_CATEGORIA_ID_CATEGORIA IN (
        IF (IFNULL(IMOVEL, FALSE) = FALSE AND IFNULL(MOVEL, FALSE) = FALSE AND IFNULL(MATERIAL, FALSE) = FALSE, TRUE, NULL),
        
        IF (IFNULL(IMOVEL, FALSE)   = TRUE, 1, NULL),
        IF (IFNULL(MOVEL, FALSE)    = TRUE, 2, NULL),
        IF (IFNULL(MATERIAL, FALSE) = TRUE, 3, NULL)
      )
    AND (
      SELECT
        TRUE
      FROM
        TB_IMOVEL
      WHERE
        TB_IMOVEL.NR_PET = IF(PETS = TRUE, 1, TB_IMOVEL.NR_PET)
      AND
        TB_IMOVEL.ID_IMOVEL = ANUNCIO.TB_IMOVEL_IDTB_IMOVEL
      )
    AND
      ANUNCIO.NR_VALOR BETWEEN IFNULL(MIN_VALOR, 0) AND IFNULL(MAX_VALOR, 10000000000)
    GROUP BY ANUNCIO.ID_ANUNCIO
    ORDER BY
      CASE ORDENACAO
        WHEN '1' THEN ANUNCIO.NR_VALOR 
        WHEN '2' THEN ANUNCIO.NR_VALOR
        WHEN '3' THEN ANUNCIO.DS_TITULO
        ELSE ANUNCIO.ID_ANUNCIO
      END;

END //

DELIMITER ;