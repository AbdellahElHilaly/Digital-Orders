SELECT *
FROM `equipment_piece` ep
WHERE `equipment_id` = 5
  AND (
            `status` = 'AVAILABLE'
        OR
            CASE
                WHEN ep.`reserved_date` IS NOT NULL AND ep.`return_date` IS NOT NULL THEN
                            ep.`reserved_date` > '2023-05-06 00:00:00'
                        OR
                            ep.`return_date` < '2023-05-03 00:00:00'

                ELSE
                    TRUE
                END
    );
