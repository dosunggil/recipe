SELECT sc_num, sc_label, sc_division
FROM tbl_sch
WHERE SUBSTR(sc_num,1,2) = '03'
GROUP BY sc_num, sc_label, sc_division
ORDER BY sc_num;
