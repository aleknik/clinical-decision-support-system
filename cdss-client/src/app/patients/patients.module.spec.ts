import { PatientsModule } from './patients.module';

describe('PatientsModule', () => {
  let patientsModule: PatientsModule;

  beforeEach(() => {
    patientsModule = new PatientsModule();
  });

  it('should create an instance', () => {
    expect(patientsModule).toBeTruthy();
  });
});
