select count(id) as 'jobCount', licenceType_Id 
from JobRequest where jobStatus = 'ACCEPTED' and dateInitiated>DATE_ADD(NOW(),INTERVAL -1 MONTH) 
group by licenceType_id order by jobCount desc