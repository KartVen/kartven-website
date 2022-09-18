import './ProjectsList.css';
import {Link} from "react-router-dom";
import useFetchData from "../../hooks/useFetchData";
import {useState} from "react";
import {FiExternalLink, FiFolder} from "react-icons/fi";

const ProjectsList = () => {
    const iconSize = 20;

    const {data: categories, status} = useFetchData('/api/category');
    const [categoryId, setCategoryId] = useState(0);

    return (
        <div className="links-table">
            <div className="category-list">
                {
                    <ul>
                        {
                            status && categories.map(({id, name}) => (
                                <li key={id}>
                                    <button onClick={() => setCategoryId(id)}>{name}</button>
                                </li>
                            ))
                        }
                    </ul>
                }
            </div>
            <div className="links-list">
                {
                    <ul>
                        {
                            categories.length > 0 &&
                            categories.map(({id, listOfLink}) => categoryId === id && listOfLink.map(({id, name, href}) => (
                                <li key={id}>
                                    <Link to={href}>
                                        <p className="link-cnt"><FiFolder size={iconSize} className="folder-ico"/>{name}</p>
                                        <div className="link-tool">
                                            <FiExternalLink size={iconSize}/>
                                        </div>
                                    </Link>
                                </li>
                            )))
                        }
                    </ul>
                }
            </div>
        </div>
    );

}

export default ProjectsList;