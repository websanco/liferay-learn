import React, {useState} from 'react';

import {deleteObject} from '../utils/Requests';

function DeleteForm() {
	const [id, setId] = useState('');

	const handleSubmit = () => {
		deleteObject(id);
	};

	return (
		<div className="App">
			<h2>Delete a J4X7 Object</h2>

			<form onSubmit={handleSubmit}>
				<input
					onChange={object => setId(object.target.value)}
					placeholder="id"
					type="text"
					value={id}
				/>

				<button type="submit">Delete</button>
			</form>
		</div>
	);
}

export default DeleteForm;
