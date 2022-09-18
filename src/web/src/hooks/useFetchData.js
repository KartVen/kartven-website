import {useEffect, useState} from 'react';
import axios from 'axios';

const useFetchData = (request) => {
    const [status, setStatus] = useState(false);
    const [data, setData] = useState({});

    useEffect(() => {
        const fetchData = async () => {
            try {
                const {data: res} = await axios.get(request);
                setData(res);
            } catch (error) {
                console.error(error)
            }
            setStatus(true);
        };

        fetchData();
    }, [request]);

    return {
        data,
        status,
    };
};

export default useFetchData;