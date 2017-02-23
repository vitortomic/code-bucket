SELECT
eng.id, 
CAST(eng.startingDate as DATE) as 'Date',
(SELECT CONCAT(CONCAT(firstName,' '),lastName)
	FROM user
    WHERE user.id=eng.personnel_id
) as 'Professional',
eng.totalAmount as 'Total amount',
COALESCE(
(SELECT sum(tr.discountEarnedClient) 
	FROM engagementday ed join transaction tr on ed.id=tr.engagementDay_id 
    WHERE ed.engagement_id=eng.id
),0) as 'Discount'
FROM engagement eng 
WHERE eng.client_id=8453 and eng.status='COMPLETED'
