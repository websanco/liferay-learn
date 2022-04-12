import AddForm from './AddForm';
import DeleteForm from './DeleteForm';
import GetForm from './GetForm';
import PatchForm from './PatchForm';

function App() {
	return (
		<div>
			<GetForm />
			<AddForm />
			<PatchForm />
			<DeleteForm />
		</div>
	);
}

export default App;
