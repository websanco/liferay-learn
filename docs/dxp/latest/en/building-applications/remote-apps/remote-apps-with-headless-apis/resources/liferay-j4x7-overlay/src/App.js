import AddForm from './components/AddForm';
import DeleteForm from './components/DeleteForm';
import GetForm from './components/GetForm';
import PatchForm from './components/PatchForm';

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
