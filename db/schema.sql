# ALTER TABLE risk_grid DROP COLUMN geom_wkt;
#ALTER TABLE risk_grid
#MODIFY geom POLYGON NOT NULL;

#ALTER TABLE risk_grid
#ADD SPATIAL INDEX idx_geom (geom);
use londonsafetymap;
SELECT log_risk_score
FROM risk_grid
WHERE ST_Contains(
    geom,
    ST_SRID(POINT(531000, 179000), 27700)
)
LIMIT 1;
