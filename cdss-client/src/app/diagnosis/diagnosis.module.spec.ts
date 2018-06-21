import { DiagnosisModule } from './diagnosis.module';

describe('DiagnosisModule', () => {
  let diagnosisModule: DiagnosisModule;

  beforeEach(() => {
    diagnosisModule = new DiagnosisModule();
  });

  it('should create an instance', () => {
    expect(diagnosisModule).toBeTruthy();
  });
});
